package com.alekseyld.formulaconverter.navigator;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum NavigatorImpl_Factory implements Factory<NavigatorImpl> {
  INSTANCE;

  @Override
  public NavigatorImpl get() {
    return new NavigatorImpl();
  }

  public static Factory<NavigatorImpl> create() {
    return INSTANCE;
  }
}
