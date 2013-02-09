package com.awar.ags.handlers

import org.jboss.netty.channel.{Channel, ChannelHandlerContext}
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.replay.{VoidEnum, ReplayingDecoder}

/**
 * Created by IntelliJ IDEA.
 * User: Solverit
 * Date: 01.12.10
 * Time: 19:12
 */

class MessageFramer extends ReplayingDecoder[ DecoderState ] ( DecoderState.READ_LENGTH )
{
  var length = 0

  def decode( ctx: ChannelHandlerContext, ch: Channel, cb: ChannelBuffer, state: DecoderState ): Object =
  {
    state match
    {
      case DecoderState.READ_LENGTH =>
        length = cb.readInt()
        checkpoint( DecoderState.READ_CONTENT )

        null

      case DecoderState.READ_CONTENT =>
        val frame: ChannelBuffer = cb.readBytes( length )
        checkpoint( DecoderState.READ_LENGTH )

        frame
    }

  }

}

