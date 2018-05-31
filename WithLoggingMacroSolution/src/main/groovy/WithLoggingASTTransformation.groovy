import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.tools.GeneralUtils.constX

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingASTTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        def node = nodes[1]
        if (node instanceof MethodNode) {
            processMethod(node)
        } else if (node instanceof ClassNode) {
            node.methods.findAll{ !it.name.contains('$') }.each {
                processMethod(it)
            }
        }
    }

    private static void processMethod(MethodNode method) {
        def msg = constX(method.name)
        def startStmt = macro(true) { println 'Starting ' + $v { msg } }
        def endStmt = macro(true) { println 'Ending ' + $v { msg } }
        if (method instanceof ConstructorNode) {
            startStmt = macro(true) { println 'Created on ' + new java.util.Date() }
            endStmt = null
        }
        method.code.statements.add(0, startStmt)
        if (endStmt) method.code.statements << endStmt
    }
}
