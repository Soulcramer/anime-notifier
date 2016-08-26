package moe.notify.animenotifier.domain.interactors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.impl.GetAnimeInteractorImpl;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.threading.TestMainThread;

import static org.mockito.Mockito.when;

public class GetAnimeInteractorlTest {
    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private AnimeRepository mCostRepository;
    @Mock
    private GetAnimeInteractor.Callback mMockedCallback;

    private long animeId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
        animeId = 100; // any number will do as cost ID
    }

    @Test
    public void testAnimeNotFound() throws Exception {
        GetAnimeInteractorImpl interactor = new GetAnimeInteractorImpl(mExecutor, mMainThread, mCostRepository, mMockedCallback, animeId);
        interactor.run();

        Mockito.verify(mCostRepository).getById(animeId);
        Mockito.verifyNoMoreInteractions(mCostRepository);
    }

    @Test
    public void testAnimeFound() throws Exception {

        Anime dummyAnime = new Anime();
        when(mCostRepository.getById(animeId))
                .thenReturn(dummyAnime);

        GetAnimeInteractorImpl interactor = new GetAnimeInteractorImpl(mExecutor, mMainThread, mCostRepository, mMockedCallback, animeId);
        interactor.run();

        Mockito.verify(mCostRepository).getById(animeId);
        Mockito.verifyNoMoreInteractions(mCostRepository);
        Mockito.verify(mMockedCallback).onAnimeRetrieved(dummyAnime);
    }
}