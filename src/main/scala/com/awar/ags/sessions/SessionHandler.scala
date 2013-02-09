package com.awar.ags.sessions

import org.slf4j.{LoggerFactory, Logger}


/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 14.12.10
 * Time: 4:05
 */

class SessionHandler( session: Session )
{
  val log: Logger = LoggerFactory.getLogger ( getClass )

  // -----
//  protected def receive =
//  {
//    case msg: Array[ Byte ] =>
//
////      val comm: Protocol.ProtocolMSG = Protocol.ProtocolMSG.parseFrom ( msg )
////
////      log.info ( comm.getLoginreq.getName )
//
//    case packet: Long =>
//      println ( "out" )
//
//  }
}