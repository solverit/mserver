package com.awar.ags.sessions


import scala.collection.JavaConversions._
import java.util.concurrent.ConcurrentHashMap
import org.jboss.netty.channel.Channel
import java.net.InetSocketAddress
import collection.mutable
import org.slf4j.{LoggerFactory, Logger}

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 14.12.10
 * Time: 4:15
 */

object SessionManager
{
  val log: Logger = LoggerFactory.getLogger( getClass )

  val sessionsByChannel: mutable.HashMap[ Channel, Session ] = new mutable.HashMap[ Channel, Session ]

  def createSession( ch: Channel ): Session =
  {
    val session = new Session( ch )
    val addr: InetSocketAddress = ch.getLocalAddress.asInstanceOf[ InetSocketAddress ]

    log.info( "Create Session: {}, from: {}", session.getId, addr )

    session
  }

  // -----
  def addSession( session: Session )
  {
    sessionsByChannel.put( session.getChannel, session )
  }

  // ----- session -----
  def getSessionByChannel( ch: Channel ): Option[Session] =
  {
    sessionsByChannel.get( ch )
  }

}