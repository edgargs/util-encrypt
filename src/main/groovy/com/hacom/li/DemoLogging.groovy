package com.hacom.li

import com.hacom.li.util.LIEncryption
import groovy.util.logging.Slf4j

/**
 * Created by Edgar Rios on 30/10/2017.
 */
@Slf4j
class DemoLogging {

    static void main(String[] args) {
        LIEncryption.keyEncrypt = "holamundholamund"
        (1..10).each {
            it -> log.info "$it) Prueba de encriptacion"
        }
    }
}
