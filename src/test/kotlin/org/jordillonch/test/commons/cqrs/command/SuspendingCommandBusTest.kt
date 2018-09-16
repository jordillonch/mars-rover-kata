package org.jordillonch.test.commons.cqrs.command

import io.kotlintest.specs.ShouldSpec
import kotlinx.coroutines.experimental.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jordillonch.test.commons.assertions.assertFailsWith
import org.jordillonch.commons.cqrs.command.domain.Command
import org.jordillonch.commons.cqrs.command.domain.CommandHandler
import org.jordillonch.commons.cqrs.command.domain.NoCommandHandlerFound
import org.jordillonch.commons.cqrs.command.infrastructure.SuspendingCommandBus
import org.jordillonch.test.commons.faker.Faker

class SuspendingCommandBusTest : ShouldSpec(
        {
            should("it should register a handler and then query it") {
                runBlocking {
                    val bus = SuspendingCommandBus()

                    bus.registerHandler(TestCommandHandler())

                    val testValue = Faker.instance().number().randomNumber()
                    val command = TestCommand(testValue)

                    assertThat(bus.handle(command), equalTo(testValue))
                }
            }

            should("it should fail because no registered handler") {
                runBlocking {
                    val bus = SuspendingCommandBus()
                    assertFailsWith(NoCommandHandlerFound::class) {
                        bus.handle<Int>(TestCommand(1))
                    }
                    Unit
                }
            }

        })

private data class TestCommand(val id: Long) : Command

private class TestCommandHandler : CommandHandler<TestCommand, Long> {
    override suspend fun on(command: TestCommand): Long {
        return command.id
    }
}
