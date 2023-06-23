package com.airwallex.issuing.gpt.persistence

import java.sql.DriverManager
import javax.sql.DataSource

class BinRepository {
    val connection = DriverManager.getConnection("jdbc:h2:~/bindb", "sa", "")

    fun getBin(key: String): String {
        val statement = connection.createStatement();
        val sql = "select bin from db where id='${key}'"
        val rs = statement.executeQuery(sql)
        if (rs.next()) {
            return rs.getString("BIN").orEmpty()
        }
        return ""
    }
}