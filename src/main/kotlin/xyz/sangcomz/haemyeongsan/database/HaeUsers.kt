package xyz.sangcomz.haemyeongsan.database

import org.h2.engine.User
import org.jetbrains.squash.definition.*

object HaeUsers : TableDefinition() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 128)
}