import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { LayoutService,AppConfig} from './app.layout.service';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

    constructor(private primengConfig: PrimeNGConfig, private layoutService: LayoutService) { }

    ngOnInit(): void {
        this.primengConfig.ripple = true;       // Enables core ripple functionality

        // Optional configuration with the default configuration
        const config: AppConfig = {
            ripple: false,                      // Toggles ripple on and off
            inputStyle: 'outlined',             // Default style for input elements
            menuMode: 'static',                 // Layout mode of the menu, valid values are "static" and "overlay"
            colorScheme: 'light',               // Color scheme of the template, valid values are "light" and "dark"
            theme: 'lara-light-indigo',         // Default component theme for PrimeNG
            scale: 14                           // Size of the body font size to scale the whole application
        };
        this.layoutService.set(config);
    }

}
