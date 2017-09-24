package com.alekseyld.formulaconverter.internal.di.module;

import com.alekseyld.formulaconverter.UIThread;
import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvidePostExecutionThreadFactory
    implements Factory<PostExecutionThread> {
  private final ApplicationModule module;

  private final Provider<UIThread> uiThreadProvider;

  public ApplicationModule_ProvidePostExecutionThreadFactory(
      ApplicationModule module, Provider<UIThread> uiThreadProvider) {
    assert module != null;
    this.module = module;
    assert uiThreadProvider != null;
    this.uiThreadProvider = uiThreadProvider;
  }

  @Override
  public PostExecutionThread get() {
    return Preconditions.checkNotNull(
        module.providePostExecutionThread(uiThreadProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PostExecutionThread> create(
      ApplicationModule module, Provider<UIThread> uiThreadProvider) {
    return new ApplicationModule_ProvidePostExecutionThreadFactory(module, uiThreadProvider);
  }
}
