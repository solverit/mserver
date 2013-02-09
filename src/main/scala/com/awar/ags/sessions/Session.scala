package com.awar.ags.sessions

import org.slf4j.{LoggerFactory, Logger}
import java.util.concurrent.atomic.AtomicInteger
import org.jboss.netty.channel.Channel
import java.lang.String
import beans.BeanProperty

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 01.12.10
 * Time: 19:30
 */

class Session( ch: Channel )
{
  val log: Logger = LoggerFactory.getLogger( getClass )

  val NO_ADRESS = "[unknown]"

  // ----- fields -----
  val idCounter: AtomicInteger = new AtomicInteger( 0 )

  @BeanProperty
  var id: Long = 0L

  var channel: Channel = null

//  var handler: ActorRef =  new SessionHandler( this ).self

  @BeanProperty
  var clientIpAddress = NO_ADRESS

  @BeanProperty
  var clientPort = NO_ADRESS

  @BeanProperty
  var connected = false


  // ----- getters ans setters
  def getChannel = channel

  def setConnection( channel: Channel )
  {
    if( channel == null ) return

    if( this.channel != null )
    {
      throw new IllegalArgumentException( "You cannot overwrite the connection linked to a Session!" )
    }
    else
    {
      this.channel = channel

      if( channel.isConnected )
      {
        this.connected = true
        val host = channel.getRemoteAddress.toString
        val adr: Array[ String ] = host.split( "\\:" )
        this.clientIpAddress = adr( 0 )
        this.clientPort = adr( 1 )
      }
      else
      {
        this.clientIpAddress = NO_ADRESS
        this.clientPort      = NO_ADRESS
      }
    }
  }

  // ----- destroy -----
  def close()
  {
    channel.close()
  }

  // ----- override -----
  override def toString =
    "{ Id: %d, Connected: %s, IP: %s }".format( this.id, this.connected, this.clientIpAddress )
}