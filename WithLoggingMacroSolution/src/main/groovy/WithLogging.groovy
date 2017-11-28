import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR])
@GroovyASTTransformationClass(["WithLoggingASTTransformation"])
@interface WithLogging { }
