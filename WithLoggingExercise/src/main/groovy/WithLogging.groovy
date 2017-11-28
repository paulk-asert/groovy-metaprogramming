import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(["WithLoggingASTTransformation"])
@interface WithLogging { }
