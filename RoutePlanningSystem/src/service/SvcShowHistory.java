package service;

public class SvcShowHistory extends UnrecordedService{
	public void execute() {
		RecordedService.showHistory();
	}

}
