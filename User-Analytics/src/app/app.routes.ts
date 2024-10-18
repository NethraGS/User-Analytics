import { Routes } from '@angular/router';
import { UserlayoutComponent } from './User/userlayout/userlayout.component';
import { MapviewComponent } from './User/mapview/mapview.component';
import { DashboardComponent } from './User/dashboard/dashboard.component';
import { SchedulerComponent } from './User/scheduler/scheduler.component';
import { DimmingProfilesComponent } from './User/scheduler/dimming-profiles/dimming-profiles.component';
import { DimmingSchedulesComponent } from './User/scheduler/dimming-schedules/dimming-schedules.component';

export const routes: Routes = [   // Add 'export' here to export routes
    {
        path: '',
        component: UserlayoutComponent,
        children: [
            { path: 'dashboard', component: DashboardComponent },
            { path: 'mapview', component: MapviewComponent },
            {
                path: 'scheduler',
                children: [
                    { path: 'dimming-profiles', component: DimmingProfilesComponent },
                    { path: 'dimming-schedules', component: DimmingSchedulesComponent }
                ]
            },
            { path: '', redirectTo: '/dashboard', pathMatch: 'full' }
        ]
    }
];
