import {
  AfterViewChecked,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  inject,
} from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CardComponent } from '../../shared/components/card/card.component';
import { CardData } from '../../models/card.model';
import { CommonModule } from '@angular/common';
import { MatchService } from '../../services/match/match.service';
import { Message } from '../../models/message.model';
import { WebsocketService } from '../../services/websocket/websocket.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule, CardComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit, AfterViewChecked {
  private matchService = inject(MatchService);
  cardItems: CardData[] = [];
  staticCardItems: CardData[] = [
    {
      title: 'Participe do chat',
      description:
        'Mostre a força do torcedor da fúria e intereja com outros torcedores',
    },
  ];

  messages: Message[] = [];
  isConnected: boolean = false;
  error: string = '';
  @ViewChild('chatMessages') private chatMessagesContainer!: ElementRef;

  form: FormGroup = new FormGroup({
    user: new FormControl<string>('', Validators.required),
    text: new FormControl<string>(
      { value: '', disabled: true },
      Validators.required
    ),
  });

  constructor(private websocketService: WebsocketService) {}

  ngOnInit(): void {
    this.loadMatches();
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  private loadMatches(): void {
    this.matchService.getMatches().subscribe({
      next: (data) => {
        this.cardItems = [...this.staticCardItems, ...data];
        console.log(this.cardItems);
      },
      error: (err: any) => {
        console.error('Erro ao carregar dados:', err);
        this.error = 'Não foi possível carregar dados';
      },
    });
  }

  connect(): void {
    const user = this.form.get('user')?.value?.trim();
    if (user) {
      this.websocketService.listen((message) => {
        this.messages.push(message);
      });

      this.isConnected = true;
      this.form.get('user')?.disable();
      this.form.get('text')?.enable();
    }
  }

  disconnect(): void {
    this.websocketService.ngOnDestroy();
    this.isConnected = false;
    this.form.get('user')?.enable();
    this.form.get('text')?.disable();
  }

  sendMessage(): void {
    const user = this.form.get('user')?.value;
    const text = this.form.get('text')?.value;
    if (user && text) {
      this.websocketService.sendMessage(user, text);
      this.form.get('text')?.reset();
    }
  }

  private scrollToBottom(): void {
    if (this.chatMessagesContainer) {
      const element = this.chatMessagesContainer.nativeElement;
      element.scrollTop = element.scrollHeight;
    }
  }
}
