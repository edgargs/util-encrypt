package com.hacom.li.util

import ch.qos.logback.classic.pattern.ClassicConverter
import ch.qos.logback.classic.spi.ILoggingEvent

/**
 * Created by Edgar Rios on 20/10/2017.
 */
class CryptoConverter extends ClassicConverter{

    @Override
    String convert(ILoggingEvent event) {
        return LIEncryption.encrypt(event.message)
    }
}
