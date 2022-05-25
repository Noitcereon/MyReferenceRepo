export interface IMission {
  mission_id: number;
  name: String;
  is_human_visible: String;
  is_zombie_visible: String;
  description: String;
  start_time: String; // String as date time
  end_time: String; // String as date time
}
