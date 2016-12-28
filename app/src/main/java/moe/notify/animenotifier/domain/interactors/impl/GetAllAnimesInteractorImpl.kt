package moe.notify.animenotifier.domain.interactors.impl

import moe.notify.animenotifier.domain.executor.Executor
import moe.notify.animenotifier.domain.executor.MainThread
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor
import moe.notify.animenotifier.domain.interactors.base.AbstractInteractor
import moe.notify.animenotifier.domain.repository.AnimeListRepository

class GetAllAnimesInteractorImpl(threadExecutor: Executor, mainThread: MainThread,
    private val animeListRepository: AnimeListRepository, private val mCallback: GetAllAnimesInteractor.Callback,
    private val username: String) : AbstractInteractor(threadExecutor,
    mainThread), GetAllAnimesInteractor {

  override fun run() {
    val animeList = animeListRepository.getByUser(username)

    mainThread.post { mCallback.onAnimeListRetrieved(animeList) }
  }
}
