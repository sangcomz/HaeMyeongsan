package xyz.sangcomz.haemyeongsan.database

import kotlinx.io.core.Closeable
import xyz.sangcomz.haemyeongsan.model.HaeUser

interface UsersStorage : Closeable {

    fun createUser(user: HaeUser): HaeUser

    fun getUser(id: Int): HaeUser?

    fun getAll(): List<HaeUser>
}