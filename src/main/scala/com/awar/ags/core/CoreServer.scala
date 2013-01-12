package com.awar.ags.core

import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import java.util.concurrent.{ExecutorService, Executors}
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.{Channels, ChannelPipelineFactory}
import java.net.InetSocketAddress
import org.slf4j.{LoggerFactory, Logger}
import com.awar.ags.handlers.{ConnectionHandler, MessageFramer, ChannelHandler}

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 01.12.10
 * Time: 17:24
 */

object CoreServer
{
  val log: Logger = LoggerFactory.getLogger( getClass )

  var factory: NioServerSocketChannelFactory = null
  var channelHandler: ChannelHandler = null
  var channelFramer:  MessageFramer = null
  var channelConnect: ConnectionHandler = null

  def init()
  {
    // Configure the thread.
    val bossExec:   ExecutorService = Executors.newFixedThreadPool( 2 )
    val workerExec: ExecutorService = Executors.newCachedThreadPool()
    val workerCount = 4

    factory = new NioServerSocketChannelFactory( bossExec, workerExec, workerCount )

    log.info( "Starting server" )

    initGeneralChannelHandler()

    log.info( "Start server: ok" )
  }

  def initGeneralChannelHandler()
  {
    // Configure the server.
    val bootstrap  = new ServerBootstrap( factory )
    channelHandler = new ChannelHandler()
    channelFramer  = new MessageFramer()
    channelConnect = new ConnectionHandler()

    // Set up the pipeline factory.
    val chpf = new ChannelPipelineFactory
    {
      def getPipeline =
      {
        val pipeline = Channels.pipeline()

        pipeline.addLast( "connect", channelConnect )
        pipeline.addLast( "frame",   channelFramer )
        pipeline.addLast( "handler", channelHandler )

        pipeline
      }
    }

    bootstrap.setPipelineFactory( chpf )

    //Socket options
    bootstrap.setOption( "child.tcpNoDelay", true )
    bootstrap.setOption( "child.keepAlive", true )
    bootstrap.setOption( "reuseAddress", true )

    // Bind and start to accept incoming connections.
    bootstrap.bind( new InetSocketAddress( 8888 ) )
  }

}