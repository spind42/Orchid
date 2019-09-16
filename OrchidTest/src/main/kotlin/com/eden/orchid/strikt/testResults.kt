package com.eden.orchid.strikt

import com.eden.orchid.testhelpers.TestRenderer
import com.eden.orchid.testhelpers.TestResults
import strikt.api.Assertion

/**
 * Assert that the site built cleanly, no exceptions were thrown, and that at least one page was rendered.
 */
fun Assertion.Builder<TestResults>.somethingRendered(): Assertion.Builder<TestResults> =
    assert("at least one page was rendered") {
        if (!it.isRenderingSuccess || it.thrownException != null)
            fail("rendering was not successful")
        else if (it.renderedPageMap.isEmpty())
            fail("no pages were rendered")
        else
            pass()
    }

/**
 * Assert that the site built cleanly, no exceptions were thrown, but that no pages were rendered.
 */
fun Assertion.Builder<TestResults>.nothingRendered(): Assertion.Builder<TestResults> =
    assert("no pages were rendered") {
        if (!it.isRenderingSuccess || it.thrownException != null)
            fail("rendering was not successful")
        else if (it.renderedPageMap.isNotEmpty())
            fail("${it.renderedPageMap.size} pages were rendered")
        else
            pass()
    }

/**
 * Assert that the site built cleanly, no exceptions were thrown, and that a specific number of pages were rendered
 */
fun Assertion.Builder<TestResults>.pagesGenerated(size: Int): Assertion.Builder<TestResults> =
    assert("exactly $size pages were rendered") {
        if (!it.isRenderingSuccess || it.thrownException != null)
            fail("rendering was not successful")
        else if (it.renderedPageMap.size != size)
            fail("${it.renderedPageMap.size} pages were rendered")
        else
            pass()
    }

fun Assertion.Builder<TestResults>.nothingElseRendered(): Assertion.Builder<TestResults> =
    assert("all pages have been evaluated") {
        if (!it.isRenderingSuccess || it.thrownException != null) {
            fail("rendering was not successful")
        } else if (!it.renderedPageMap.all { page -> page.value.evaluated }) {
            val pagesNotEvaluated = it.renderedPageMap.filterNot { page -> page.value.evaluated }
            compose("${pagesNotEvaluated.size} pages were not evaluated") {
                pagesNotEvaluated.forEach { element ->
                    assert(element.value.path) { fail() }
                }
            } then {
                fail()
            }
        } else {
            pass()
        }
    }

fun Assertion.Builder<TestResults>.pageWasRendered(
    name: String,
    block: Assertion.Builder<TestRenderer.TestRenderedPage>.() -> Unit = {}
): Assertion.Builder<TestResults> =
    assertBlock("page was rendered at $name") {
        val expectedPage = it.renderedPageMap[name]
        if (expectedPage != null) {
            get { expectedPage!! }.block()
            expectedPage.evaluated = true
        } else {
            AssertBlockFailure("page was not rendered")
        }
    }

fun Assertion.Builder<TestResults>.pageWasNotRendered(name: String): Assertion.Builder<TestResults> =
    assert("page was not rendered at $name") {
        if (!it.isRenderingSuccess || it.thrownException != null)
            fail("rendering was not successful")
        else {
            val page = it.renderedPageMap[name]
            if (page != null) {
                fail("page was rendered")
            } else {
                pass()
            }
        }
    }

fun Assertion.Builder<TestResults>.printResults(): Assertion.Builder<TestResults> =
    assertThat("printing test results") {
        get { this.printResults() }

        true
    }

fun <T> Assertion.Builder<T>.assertBlock(
    description: String,
    block: Assertion.Builder<T>.(T) -> Any?
): Assertion.Builder<T> {
    var message: Any? = null

    return compose(description) {
        message = block(it)
    }.then {
        when {
            anyFailed -> fail()
            message is AssertBlockFailure -> fail((message as AssertBlockFailure).errorMessage)
            else -> pass()
        }
    }
}

data class AssertBlockFailure(val errorMessage: String)