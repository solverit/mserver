package com.awar.ags.handlers

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel._
import org.slf4j.{LoggerFactory, Logger}
import com.awar.ags.sessions.SessionManager

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 14.12.10
 * Time: 17:27
 */

class ChannelHandler extends SimpleChannelHandler
{
  val log: Logger = LoggerFactory.getLogger( getClass )

  override def messageReceived( ctx: ChannelHandlerContext, e: MessageEvent )
  {
    val msg = e.getMessage.asInstanceOf[ ChannelBuffer ].array()

    var session = SessionManager.getSessionByChannel( e.getChannel )
    if ( session == null )
    {
      session = SessionManager.createSession( e.getChannel )
    }

//    session.handler ! msg
  }

}