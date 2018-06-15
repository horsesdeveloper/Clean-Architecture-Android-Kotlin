package com.horses.architecture.domain.usecase

import com.horses.architecture.data.repository.JedisRepository
import com.horses.architecture.domain.model.Jedi
import com.horses.architecture.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetJedis(
        executorThread: Scheduler,
        uiThread: Scheduler,
        var repository: JedisRepository
) : UseCase<List<Jedi>>(executorThread, uiThread){

    override fun createObservableUseCase(): Observable<List<Jedi>> = repository.jediList().toObservable()

}