package moe.notify.animenotifier.domain.interactors;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.impl.GetAllAnimesInteractorImpl;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeListRepository;
import moe.notify.animenotifier.threading.TestMainThread;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class GetAllAnimesInteractorImplTest {
  @Mock Executor mExecutor;
  @Mock AnimeListRepository animeListRepository;
  @Mock GetAllAnimesInteractor.Callback mMockedCallback;
  private MainThread mMainThread;
  private String username;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mMainThread = new TestMainThread();
    username = "Scott"; // any number will do as cost ID
  }

  @Test public void testAnimeNotFound() throws Exception {
    GetAllAnimesInteractorImpl interactor =
        new GetAllAnimesInteractorImpl(mExecutor, mMainThread, animeListRepository, mMockedCallback,
            username);
    interactor.run();

    Mockito.verify(animeListRepository).getByUser(username);
    Mockito.verifyNoMoreInteractions(animeListRepository);
  }

  @Test public void testAnimeFound() throws Exception {

    AnimeList dummyAnimeList = new AnimeList();
    when(animeListRepository.getByUser(username)).thenReturn(dummyAnimeList);

    GetAllAnimesInteractorImpl interactor =
        new GetAllAnimesInteractorImpl(mExecutor, mMainThread, animeListRepository, mMockedCallback,
            username);
    interactor.run();

    Mockito.verify(animeListRepository).getByUser(username);
    Mockito.verifyNoMoreInteractions(animeListRepository);
    Mockito.verify(mMockedCallback).onAnimeListRetrieved(dummyAnimeList);
  }
}