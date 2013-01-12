package com.awar.ags.sessions


import scala.collection.JavaConversions._
import java.util.concurrent.ConcurrentHashMap
import org.jboss.netty.channel.Channel
import java.net.InetSocketAddress
import collection.mutable

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 14.12.10
 * Time: 4:15
 */

object SessionManager
{
  val sessionsByChannel: mutable.ConcurrentMap[ Channel, Session ] = new ConcurrentHashMap[ Channel, Session ]

  def createSession( ch: Channel ): Session =
  {
    val session = new Session( ch )
//    val addr: InetSocketAddress = ch.getLocalAddress.asInstanceOf[ InetSocketAddress ]

    session
  }

  // -----
  def addSession( session: Session )
  {
    sessionsByChannel.put( session.getChannel, session )
  }

  // ----- session -----
  def getSessionByChannel( ch: Channel ): Session =
  {
    val sess = sessionsByChannel.get( ch )

    if( sess == None )
      null
    else
      sess.get
  }

}