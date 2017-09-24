package com.alekseyld.formulaconverter.internal.di.component;

import android.content.Context;
import com.alekseyld.formulaconverter.UIThread;
import com.alekseyld.formulaconverter.UIThread_Factory;
import com.alekseyld.formulaconverter.executor.JobExecutor;
import com.alekseyld.formulaconverter.executor.JobExecutor_Factory;
import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import com.alekseyld.formulaconverter.executor.ThreadExecutor;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule_ProvideApplicationContextFactory;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule_ProvidePostExecutionThreadFactory;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule_ProvideRestAdapterFactory;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule_ProvideThreadExecutorFactory;
import com.alekseyld.formulaconverter.view.activity.base.BaseActivity;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
  private Provider<Context> provideApplicationContextProvider;

  private Provider<JobExecutor> jobExecutorProvider;

  private Provider<ThreadExecutor> provideThreadExecutorProvider;

  private Provider<UIThread> uIThreadProvider;

  private Provider<PostExecutionThread> providePostExecutionThreadProvider;

  private Provider<Retrofit> provideRestAdapterProvider;

  private DaggerApplicationComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideApplicationContextProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideApplicationContextFactory.create(builder.applicationModule));

    this.jobExecutorProvider = DoubleCheck.provider(JobExecutor_Factory.create());

    this.provideThreadExecutorProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideThreadExecutorFactory.create(
                builder.applicationModule, jobExecutorProvider));

    this.uIThreadProvider = DoubleCheck.provider(UIThread_Factory.create());

    this.providePostExecutionThreadProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvidePostExecutionThreadFactory.create(
                builder.applicationModule, uIThreadProvider));

    this.provideRestAdapterProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideRestAdapterFactory.create(builder.applicationModule));
  }

  @Override
  public void inject(BaseActivity baseActivity) {
    MembersInjectors.<BaseActivity>noOp().injectMembers(baseActivity);
  }

  @Override
  public Context context() {
    return provideApplicationContextProvider.get();
  }

  @Override
  public ThreadExecutor threadExecutor() {
    return provideThreadExecutorProvider.get();
  }

  @Override
  public PostExecutionThread postExecutionThread() {
    return providePostExecutionThreadProvider.get();
  }

  @Override
  public Retrofit provideRestAdapter() {
    return provideRestAdapterProvider.get();
  }

  public static final class Builder {
    private ApplicationModule applicationModule;

    private Builder() {}

    public ApplicationComponent build() {
      if (applicationModule == null) {
        throw new IllegalStateException(
            ApplicationModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerApplicationComponent(this);
    }

    public Builder applicationModule(ApplicationModule applicationModule) {
      this.applicationModule = Preconditions.checkNotNull(applicationModule);
      return this;
    }
  }
}
