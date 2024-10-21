import { Component, AfterViewInit, OnDestroy } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ColorPickerModule } from 'primeng/colorpicker';
import { DropdownModule } from 'primeng/dropdown';
import { CheckboxModule } from 'primeng/checkbox';
import { PaginatorModule } from 'primeng/paginator';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';
import { InputNumberModule } from 'primeng/inputnumber';
import { CalendarModule } from 'primeng/calendar';

interface TimeSetting {
  startTime: string | Date;
  lampLevel: number;
  motion: boolean;
  photocell: boolean;
  isEditing?: boolean;  
}

@Component({
  selector: 'app-dimming-profiles',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ColorPickerModule,
    DropdownModule,
    CheckboxModule,
    PaginatorModule,
    ButtonModule,
    TableModule,
    InputTextModule,
    PanelModule,
    InputNumberModule,
    CalendarModule
  ],
  templateUrl: './dimming-profiles.component.html',
  styleUrls: ['./dimming-profiles.component.css'],
})
export class DimmingProfilesComponent implements AfterViewInit, OnDestroy {
  color: string = '#ff0000'; // Default color
  selectedProfileType: string | undefined;
  profileTypes = [
    { label: '24 Hours Profile', value: '24-hours' },
    { label: 'Custom Profile', value: 'custom' },
  ];

  times: TimeSetting[] = [
    { startTime: '00:00', lampLevel: 100, motion: false, photocell: false },
    { startTime: 'SUNRISE', lampLevel: 100, motion: false, photocell: false },
    { startTime: 'SUNSET', lampLevel: 100, motion: false, photocell: false },
    { startTime: '00:00', lampLevel: 100, motion: false, photocell: false },
  ];

  ngAfterViewInit() {}

  ngOnDestroy() {}

  addTime() {
    this.times.push({ startTime: '00:00', lampLevel: 0, motion: false, photocell: false });
  }

  removeTime(time: TimeSetting) {
    this.times = this.times.filter(t => t !== time);
  }

  editTime(time: TimeSetting) {
    time.isEditing = true;
  }

  saveEdit(time: TimeSetting) {
    time.isEditing = false;  // Exit editing mode after saving changes
  }

  cancelEdit(time: TimeSetting) {
    time.isEditing = false;  // Cancel editing without saving
  }

  resetProfile() {
    // Logic to reset the profile fields
  }

  saveProfile() {
    // Logic to save the profile
  }
}
