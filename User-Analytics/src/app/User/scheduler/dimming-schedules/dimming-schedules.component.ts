import { Component, OnInit } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalendarModule } from 'primeng/calendar'; 
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown'; 

interface RecurringSchedule {
  id: number;
  startDate: Date;
  endDate: Date;
  frequency: string;
}

@Component({
  selector: 'app-dimming-schedules',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    CalendarModule,
    ButtonModule,
    TableModule,
    DropdownModule 
  ],
  templateUrl: './dimming-schedules.component.html',
  styleUrls: ['./dimming-schedules.component.css']
})
export class DimmingSchedulesComponent implements OnInit {
  schedules: RecurringSchedule[] = [];
  newSchedule: RecurringSchedule = { id: 0, startDate: new Date(), endDate: new Date(), frequency: '' };

  frequencyOptions = [
    { label: 'Daily', value: 'daily' },
    { label: 'Weekly', value: 'weekly' },
    { label: 'Monthly', value: 'monthly' },
    { label: 'Custom', value: 'custom' }
  ];

  ngOnInit() {
    this.loadExistingSchedules();
  }

  loadExistingSchedules() {
    this.schedules = [
      { id: 1, startDate: new Date('2024-10-21'), endDate: new Date('2024-10-28'), frequency: 'weekly' },
      { id: 2, startDate: new Date('2024-11-01'), endDate: new Date('2024-11-10'), frequency: 'daily' }
    ];
  }

  addSchedule() {
    this.newSchedule.id = this.schedules.length + 1;
    this.schedules.push({ ...this.newSchedule });
    this.newSchedule = { id: 0, startDate: new Date(), endDate: new Date(), frequency: '' };
  }

  removeSchedule(schedule: RecurringSchedule) {
    this.schedules = this.schedules.filter(s => s.id !== schedule.id);
  }

  saveSchedules() {
    console.log('Schedules saved:', this.schedules);
  }
}
