
enum EventType {
  WORK_EVENT,
  TASK,
  SOCIAL_EVENT,
}
export class Event {
  id: string;
  name: string;
  description: string;
  scheduledDate: string;
  dueDate: string;
  eventTypeProper: EventType;
}
