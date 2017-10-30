
import com.hacom.li.util.CryptoConverter
import com.hacom.li.util.LogFileHeaderPatternLayout
//import org.springframework.boot.logging.logback.ColorConverter
//import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

//conversionRule 'clr', ColorConverter
//conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = System.getProperty("logging.path")?:'.'
/*if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}*/
def isDevelopmentMode = System.getProperty("development.mode")?true:false
def logFile = "liutil.log"
conversionRule("msgencrypt", CryptoConverter)
appender("FILE", RollingFileAppender) {
    file = "${targetDir}/${logFile}"
    append = true
    encoder(LayoutWrappingEncoder) {
        layout(LogFileHeaderPatternLayout) {
            filePath = "${targetDir}/${logFile}"
            header = "con,level,thread,msg"
            def typemsg = "%msgencrypt"
            if (isDevelopmentMode)
                typemsg = "%msg"
            pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS},%5p,[%thread],"+typemsg+"%n"
        }
    }
    rollingPolicy(SizeAndTimeBasedRollingPolicy) {
        fileNamePattern = "${targetDir}/${logFile}.%d{yyyy-MM-dd}_%i.zip" //diario
        maxFileSize = "10MB" //archivos de 10 megas
        maxHistory = 30 //historia de 30 dias
        totalSizeCap = "20GB" // total de log (todos los archivos)
    }
}
logger("com.hacom.li", DEBUG, ['FILE'], false)

root(ERROR, ['STDOUT'])
