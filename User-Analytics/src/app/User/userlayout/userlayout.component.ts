import { Component, ViewChild } from '@angular/core';
import { Sidebar, SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button'; 
import { AvatarModule } from 'primeng/avatar';  
import { RouterModule, RouterOutlet } from '@angular/router';
import { RippleModule } from 'primeng/ripple';
import { StyleClassModule } from 'primeng/styleclass';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-userlayout',
  standalone: true,
  imports: [SidebarModule, ButtonModule, AvatarModule,RouterOutlet,RippleModule,StyleClassModule,CommonModule,RouterModule],  
  templateUrl: './userlayout.component.html',
  styleUrls: ['./userlayout.component.css']
})
export class UserlayoutComponent {
  sidebarVisible: boolean = false;
  isSchedulerOpen: boolean = false; // Add this line to manage the state

  closeCallback(event: Event) {
    this.sidebarVisible = false;
  }
}
