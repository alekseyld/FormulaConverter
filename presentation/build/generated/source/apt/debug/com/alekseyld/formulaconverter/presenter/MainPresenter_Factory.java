package com.alekseyld.formulaconverter.presenter;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainPresenter_Factory implements Factory<MainPresenter> {
  private final MembersInjector<MainPresenter> mainPresenterMembersInjector;

  public MainPresenter_Factory(MembersInjector<MainPresenter> mainPresenterMembersInjector) {
    assert mainPresenterMembersInjector != null;
    this.mainPresenterMembersInjector = mainPresenterMembersInjector;
  }

  @Override
  public MainPresenter get() {
    return MembersInjectors.injectMembers(mainPresenterMembersInjector, new MainPresenter());
  }

  public static Factory<MainPresenter> create(
      MembersInjector<MainPresenter> mainPresenterMembersInjector) {
    return new MainPresenter_Factory(mainPresenterMembersInjector);
  }
}
