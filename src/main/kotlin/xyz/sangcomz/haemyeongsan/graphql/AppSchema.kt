package xyz.sangcomz.haemyeongsan.graphql

import com.github.pgutkowski.kgraphql.KGraphQL
import xyz.sangcomz.haemyeongsan.database.UsersStorage
import xyz.sangcomz.haemyeongsan.model.HaeUser

class AppSchema(private val storage: UsersStorage) {

    val schema = KGraphQL.schema {

        type<HaeUser> {
            description = "A Hae User"
        }

        query("allUser") {
            description = "get all User"

            resolver(storage::getAll)
        }
    }
}