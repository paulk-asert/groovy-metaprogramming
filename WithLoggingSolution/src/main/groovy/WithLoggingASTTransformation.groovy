import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.tools.GeneralUtils.*

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingASTTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        def node = nodes[1]
        if (node instanceof MethodNode) {
            processMethod(node)
        } else if (node instanceof ClassNode) {
            node.methods.findAll { !it.name.contains('$') }.each {
                processMethod(it)
            }
        }
    }

    private static void processMethod(MethodNode method) {
        def startMessage = createPrintlnAst("Starting $method.name")
        def endMessage = createPrintlnAst("Ending $method.name")
        def statements = method.code.statements
        statements.add(0, startMessage)
        statements << endMessage
    }

    private static Statement createPrintlnAst(String message) {
        stmt(callThisX('println', args(constX(message))))
    }
}
