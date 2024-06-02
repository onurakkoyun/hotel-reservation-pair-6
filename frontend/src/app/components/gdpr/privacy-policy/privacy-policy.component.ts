import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
    selector: 'app-privacy-policy',
    templateUrl: './privacy-policy.component.html',
    styleUrl: './privacy-policy.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PrivacyPolicyComponent { 
    constructor(public dialogRef: MatDialogRef<PrivacyPolicyComponent>) { }
    
    closeDialog() {
        this.dialogRef.close();
      }
}
