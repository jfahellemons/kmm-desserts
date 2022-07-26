package com.example.justdesserts.shared.cache

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = JustDesserts(databaseDriverFactory.createDriver())
    private val dbQuery = database.justDessertsQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllDesserts()
        }
    }

    internal fun getDesserts(): List<Dessert> {
        return dbQuery.selectAllDesserts().executeAsList()
    }

    internal fun getDessertById(dessertId: String): Dessert? {
        return dbQuery.selectDessertById(dessertId).executeAsOneOrNull()
    }

    internal fun deleteDessert(dessertId: String) {
        dbQuery.transaction {
            removeDessert(dessertId)
        }
    }

    private fun removeDessert(dessertId: String) {
        dbQuery.removeDessertById(dessertId)
    }

    internal fun updateDessert(dessert: Dessert) {
        dbQuery.transaction {
            updateDessertById(dessert)
        }
    }

    private fun updateDessertById(dessert: Dessert) {
        dbQuery.updateDessertById(
            name = dessert.name,
            description = dessert.description,
            imageUrl = dessert.imageUrl,
            id = dessert.id
        )
    }

    internal fun saveDessert(dessert: Dessert) {
        dbQuery.transaction {
            insertDessert(dessert)
        }
    }

    internal fun deleteUserState() {
        dbQuery.transaction {
            removeUserState()
        }
    }

    internal fun getUserState(): UserState? {
        return dbQuery.selectUserState().executeAsOneOrNull()
    }

    private fun removeUserState() {
        dbQuery.removeUserState()
    }

    private fun insertDessert(dessert: Dessert) {
        dbQuery.insertDessert(dessert.id, dessert.userId, dessert.name, dessert.description, dessert.imageUrl)
    }

    fun saveUserState(id: String, token: String) {
        dbQuery.transaction {
            insertUserState(id, token)
        }
    }

    private fun insertUserState(id: String, token: String) {
        dbQuery.insertUserState(id, token)
    }
}
