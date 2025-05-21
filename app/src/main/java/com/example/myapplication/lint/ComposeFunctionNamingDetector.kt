package com.example.myapplication.lint

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UMethod

class ComposeFunctionNamingDetector : Detector(), SourceCodeScanner {
    companion object {
        val ISSUE = Issue.create(
            id = "ComposeFunctionNaming",
            briefDescription = "Compose function name should start with uppercase",
            explanation = "All @Composable functions should start with an uppercase letter.",
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.ERROR,
            implementation = Implementation(
                ComposeFunctionNamingDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes() = listOf(UMethod::class.java)

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        val isComposable =
            node.uAnnotations.any { it.qualifiedName == "androidx.compose.runtime.Composable" }
        if (isComposable && node.methodName?.isNotEmpty() == true && !node.methodName!![0].isUpperCase()) {
            context.report(
                ISSUE,
                node,
                context.getNameLocation(node),
                "Compose function '${node.methodName}' should start with an uppercase letter"
            )
        }
    }

//    override fun visitMethod(context: JavaContext, node: UMethod) {
//        val isComposable = node.annotations.any { it.qualifiedName == "androidx.compose.runtime.Composable" }
//        if (isComposable && node.name.isNotEmpty() && !node.name[0].isUpperCase()) {
//            context.report(
//                ISSUE,
//                node,
//                context.getNameLocation(node),
//                "Compose function '${node.name}' should start with an uppercase letter"
//            )
//        }
//    }
}