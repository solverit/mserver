package com.awar.ags.handlers

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel._
import org.slf4j.{LoggerFactory, Logger}
import com.awar.ags.sessions.{Session, SessionManager}
import com.awar.ags.game.GameServer
import com.awar.ags.net.protocol.Protocol
import com.awar.ags.net.protocol.Protocol.ProtocolMSG

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
    // прочитаем сообщение
    val msg = e.getMessage.asInstanceOf[ ChannelBuffer ].array()

    // проверим, есть ли сессия для данного соединения. если нет, создадим
    val session: Session = SessionManager.getSessionByChannel( e.getChannel ).getOrElse(SessionManager.createSession( e.getChannel ))

    log.info( "Session: {}", session.getId )

    // десериализуем сообщение protobuff
    val comm: ProtocolMSG = Protocol.ProtocolMSG.parseFrom( msg )

    GameServer.MessageProcessor( session, comm )
  }

}