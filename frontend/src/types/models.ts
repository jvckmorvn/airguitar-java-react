export type UserSession = { token: string; userId: number; name: string; email: string };

export type Instrument = {
  id: number;
  title: string;
  description?: string;
  manufacturer: string;
  model: string;
  city: string;
  country: string;
  dailyRate: number;
  imageUrls?: string;
  ownerId: number;
};

export type Booking = {
  id: number;
  instrumentId: number;
  renterId: number;
  startDate: string;
  endDate: string;
  totalPrice: number;
};

export type Message = {
  id: number;
  conversationId: string;
  senderId: number;
  receiverId: number;
  instrumentId: number;
  content: string;
  timestamp: string;
};

export type Notification = {
  id: number;
  userId: number;
  type: "BOOKING_CREATED" | "MESSAGE_RECEIVED";
  title: string;
  body: string;
  read: boolean;
  createdAt: string;
};
