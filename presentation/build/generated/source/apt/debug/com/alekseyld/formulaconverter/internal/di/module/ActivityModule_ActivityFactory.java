package com.alekseyld.formulaconverter.internal.di.module;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ActivityModule_ActivityFactory implements Factory<Activity> {
  private final ActivityModule module;

  public ActivityModule_ActivityFactory(ActivityModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Activity get() {
    return Preconditions.checkNotNull(
        module.activity(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Activity> create(ActivityModule module) {
    return new ActivityModule_ActivityFactory(module);
  }
}
