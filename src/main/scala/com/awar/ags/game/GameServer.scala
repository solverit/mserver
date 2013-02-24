package com.awar.ags.game

import com.awar.ags.net.protocol.Protocol.{LoginResponse, ProtocolMSG}
import org.slf4j.{LoggerFactory, Logger}
import collection.mutable
import com.awar.ags.sessions.Session

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 14.12.10
 * Time: 18:27
 */
object GameServer
{
  val log: Logger = LoggerFactory.getLogger( getClass )
  var lock : AnyRef = new Object()

  val playerBySession: mutable.HashMap[ Session, Player ] = new mutable.HashMap[ Session, Player ]

  def MessageProcessor( session: Session, comm: ProtocolMSG )
  {
    lock.synchronized
    {
      if( comm.hasLoginrequest )
      {
        handleLogin( session, comm )
      }

      if( comm.hasLoginrequest )
      {

      }
    }
  }

  def handleLogin( session: Session, comm: ProtocolMSG )
  {
    val login = comm.getLoginrequest.getLogin

    log.info( "player {} login", login )

    val player: Player = new Player( session, login )

    playerBySession.put( session, player )

    log.info( "send to player session info" )

    // -----
    val lr:LoginResponse.Builder = LoginResponse.newBuilder()
    lr.setIdsess( session.getId )
    lr.setIdplay( session.getId )

    val message: ProtocolMSG.Builder = ProtocolMSG.newBuilder();
    message.setLoginresponse( lr )

    session.sendMSG( message )
  }

}
