package xyz.sangcomz.haemyeongsan.database

import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.dialects.h2.H2Connection
import org.jetbrains.squash.query.from
import org.jetbrains.squash.query.orderBy
import org.jetbrains.squash.query.select
import org.jetbrains.squash.results.ResultRow
import org.jetbrains.squash.results.get
import xyz.sangcomz.haemyeongsan.model.HaeUser

class UsersDatabase : UsersStorage {

    val db: DatabaseConnection = H2Connection.createMemoryConnection(catalogue = "DB_CLOSE_DELAY=-1")

    init {
        db.transaction {
            databaseSchema().create(arrayListOf(HaeUsers))
        }
    }

    override fun createUser(user: HaeUser): HaeUser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(id: Int): HaeUser? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<HaeUser> {
        val a = db.transaction {
            from(HaeUsers)
                    .select()
                    .orderBy(HaeUsers.id, false)
                    .execute()
                    .map {
                        it.toHaeUser()
                    }
                    .toList()
        }
        return arrayListOf(HaeUser(123123, "sangcomz"))
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

fun ResultRow.toHaeUser() = HaeUser(
        id = this[HaeUsers.id],
        name = this[HaeUsers.name]
)