import actions.ExpenseReport
import adapter.addExpense
import adapter.getMyExpenses
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class ExpensesSystemAdapterStubTests {

    private val expensesSystem = StubExpensesSystem()

    @Test
    fun `can add and retrieve expenses`() {
        val added1 = expensesSystem.addExpense("Bob", 123)
        expensesSystem.addExpense("Alice", 456)
        val added3 = expensesSystem.addExpense("Bob", 789)
        assertThat(
            expensesSystem.getMyExpenses("Bob"), equalTo(
                ExpenseReport("Bob", listOf(added3, added1))
            )
        )
    }

    @Test
    fun `can add and retrieve expenses - stub`() {
        val added1 = expensesSystem.addExpense("Bob", 123)
        expensesSystem.addExpense("Alice", 456)
        val added3 = expensesSystem.addExpense("Bob", 789)
        assertThat(
            expensesSystem.getMyExpenses("Bob"), equalTo(
                ExpenseReport("Bob", listOf(added3, added1))
            )
        )
    }

    @Test
    fun `can count expenses with client`() {
        expensesSystem.addExpense("Bob", 123)
        expensesSystem.addExpense("Alice", 456)
        expensesSystem.addExpense("Bob", 789)
        assertThat(ExpensesClient(expensesSystem).countExpenses("Bob"), equalTo(2))
    }
}
