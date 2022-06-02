export interface IPlayer {
  player_id: number;
  is_human: String; // should this be a boolean in both backend and frontend?
  is_patient_zero: String; // should this be a boolean in both backend and frontend?
  bite_code: String;
  game_id: number;
  user_id: number;
}
