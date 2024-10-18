import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideAnimations } from '@angular/platform-browser/animations';

// Update appConfig to include provideAnimations
const updatedAppConfig = {
  ...appConfig,
  providers: [
    ...appConfig.providers || [],
    provideAnimations() // Add animations support
  ]
};

bootstrapApplication(AppComponent, updatedAppConfig)
  .catch((err) => console.error(err));
