import org.junit.Rule
import spock.lang.*

import org.springframework.boot.test.OutputCapture

class WithLoggingSpec extends Specification {

    @Rule
    OutputCapture capture = new OutputCapture()

    def "capture calc output"() {
        given:
        def c = new Calc()

        when:
        c.add(100, 20, 3)
        c.mult(4, 12)

        then:
        capture.toString().trim().normalize() == '''
Starting add
Adding 100, 20, 3 gives 123
Ending add
Starting methodMissing
I don't know how to mult(4, 12)
Ending methodMissing'''.trim()
    }
}

@WithLogging
class Calc {
    @WithLogging
    static add(int... args) {
        println "Adding ${args.join(', ')} gives ${args.sum()}"
    }

    //@WithLogging
    def methodMissing(String name, args) {
        println "I don't know how to $name(${args.join(', ')})"
    }
}
