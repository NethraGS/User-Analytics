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

interface TimeSetting {
  startTime: string;
  lampLevel: number;
  motion: boolean;
  photocell: boolean;
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
    PanelModule
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

  private clockInterval: any;

  ngAfterViewInit() {
    this.drawClock();
    this.clockInterval = setInterval(() => this.drawClock(), 1000); // Update clock every second
  }

  ngOnDestroy() {
    if (this.clockInterval) {
      clearInterval(this.clockInterval); // Clear the interval when component is destroyed
    }
  }

  drawClock() {
    const canvas = document.getElementById('clockCanvas') as HTMLCanvasElement;
    const ctx = canvas.getContext('2d');

    if (!ctx) return;

    // Set the canvas size
    canvas.width = 200;
    canvas.height = 200;

    // Get the current time
    const now = new Date();
    const seconds = now.getSeconds();
    const minutes = now.getMinutes();
    const hours = now.getHours();

    // Draw clock face
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.beginPath();
    ctx.arc(100, 100, 90, 0, Math.PI * 2); // Clock face
    ctx.stroke();

    // Draw clock markings
    for (let i = 0; i < 12; i++) {
      const angle = (i * Math.PI) / 6; // 30 degrees in radians
      const x = 100 + Math.cos(angle) * 80;
      const y = 100 + Math.sin(angle) * 80;
      ctx.fillText((i + 1).toString(), x - 10, y + 5);
    }

    // Draw clock hands
    // Hour hand
    ctx.save();
    ctx.translate(100, 100);
    ctx.rotate(((hours % 12) + minutes / 60) * (Math.PI / 6)); // 30 degrees per hour
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.lineTo(0, -40);
    ctx.stroke();
    ctx.restore();

    // Minute hand
    ctx.save();
    ctx.translate(100, 100);
    ctx.rotate((minutes + seconds / 60) * (Math.PI / 30)); // 6 degrees per minute
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.lineTo(0, -60);
    ctx.stroke();
    ctx.restore();

    // Second hand
    ctx.save();
    ctx.translate(100, 100);
    ctx.rotate(seconds * (Math.PI / 30)); // 6 degrees per second
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.lineTo(0, -70);
    ctx.strokeStyle = 'red';
    ctx.stroke();
    ctx.restore();
  }

  addTime() {
    this.times.push({ startTime: '00:00', lampLevel: 0, motion: false, photocell: false });
  }

  removeTime(time: TimeSetting) {
    this.times = this.times.filter(t => t !== time);
  }

  resetProfile() {
    // Logic to reset the profile fields
  }

  saveProfile() {
    // Logic to save the profile
  }
}
