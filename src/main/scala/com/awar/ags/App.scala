package com.awar.ags

import core.CoreServer
import org.slf4j.{LoggerFactory, Logger}

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 01.12.10
 * Time: 17:24
 */

object App
{
  val log: Logger = LoggerFactory.getLogger ( getClass )

  def main( args: Array[ String ] )
  {
    CoreServer.init ()
  }

}
