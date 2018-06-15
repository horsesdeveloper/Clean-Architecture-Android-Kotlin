package com.horses.architecture.data.repository

import com.google.android.gms.tasks.Task
import com.horses.architecture.data.entity.JediEntity
import com.horses.architecture.data.firebase.FirebaseDatabaseTask
import com.horses.architecture.domain.model.Jedi

@Suppress("unused")
class JedisRepository {

    private var databaseTask: FirebaseDatabaseTask = FirebaseDatabaseTask.getInstance()

    fun jediList(): Task<List<Jedi>> = databaseTask.reference.child("jedi")
            .addListenerForSingleValueEvent()
            .continueWith { task ->
                val dataSnapshot = task.result
                val list = ArrayList<Jedi>()

                for (item in dataSnapshot.children) {
                    item.getValue(Jedi::class.java)?.let {
                        list.add(it)
                    }
                }

                list
            }

    fun jediNew(jediEntity: JediEntity): Task<Void> = databaseTask.reference.child("jedi")
            .push()
            .setValue(jediEntity)
}