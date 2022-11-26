// this whole rust app is just to interact with the CLI when i press a hotkey to send a series of
// commands to the CLI. I'm not sure if this is the best way to do this, but it works for now.
fn main() {
    // ask the user what command they want to run
    println!("What command do you want to run?");
    let mut command = String::new();
    io::stdin().read_line(&mut command).expect("Failed to read line");
}

