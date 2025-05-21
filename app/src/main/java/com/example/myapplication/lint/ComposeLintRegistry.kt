package com.example.myapplication.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class ComposeLintRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(ComposeFunctionNamingDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API
}