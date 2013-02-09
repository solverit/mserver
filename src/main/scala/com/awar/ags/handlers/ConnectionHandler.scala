package com.awar.ags.handlers

import org.jboss.netty.channel.{ExceptionEvent, ChannelStateEvent, ChannelHandlerContext, SimpleChannelHandler}
import org.slf4j.{LoggerFactory, Logger}

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 14.12.10
 * Time: 4:52
 */

class ConnectionHandler extends SimpleChannelHandler
{
  val log: Logger = LoggerFactory.getLogger( getClass )

  override def channelOpen( ctx: ChannelHandlerContext, e: ChannelStateEvent )
  {
    log.info( "Channel Open" )
  }

  override def channelClosed( ctx: ChannelHandlerContext, e: ChannelStateEvent )
  {
    log.info( "Channel Closed" )
  }

  override def exceptionCaught( ctx: ChannelHandlerContext, e: ExceptionEvent )
  {
    log.error ( "Exp: {}", e.getCause )
  }
}