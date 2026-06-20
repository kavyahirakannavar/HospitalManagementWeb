package com.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hospital.PatientRepository;

@Controller
public class HomeController {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/dashboard")
	public String dashboard(Model model) {

	    model.addAttribute("doctorCount", doctorRepository.count());
	    model.addAttribute("patientCount", patientRepository.count());
	    model.addAttribute("appointmentCount", appointmentRepository.count());

	    return "dashboard";
	}
	@GetMapping("/doctor-list")
	public String doctorList(Model model) {
	    model.addAttribute("doctors", doctorRepository.findAll());
	    return "doctor-list";
	}
	@GetMapping("/patient-list")
	public String patientList(Model model) {
	    model.addAttribute("patients", patientRepository.findAll());
	    return "patient-list";
	}
	@GetMapping("/appointment-list")
	public String appointmentList(Model model) {
	    model.addAttribute("appointments", appointmentRepository.findAll());
	    return "appointment-list";
	}
	@GetMapping("/medicine-list")
	public String medicineList(Model model) {
	    model.addAttribute("medicines", medicineRepository.findAll());
	    return "medicine-list";
	}
	@GetMapping("/medicine")
	public String medicineForm() {
	    return "medicine";
	}
	

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/doctor")
	public String doctor() {
		return "doctor";
	}

	@GetMapping("/patient")
	public String patient() {
		return "patient";
	}

	@GetMapping("/appointment")
	public String appointment() {
		return "appointment";
	}

	@PostMapping("/medicine/save")
	public String saveMedicine(@RequestParam("medicineName") String medicineName,
			@RequestParam("quantity") Integer quantity, @RequestParam("price") Double price) {

		Medicine medicine = new Medicine();
		medicine.setMedicineName(medicineName);
		medicine.setQuantity(quantity);
		medicine.setPrice(price);
		System.out.println("Medicine = " + medicineName);
		System.out.println("Quantity = " + quantity);
		System.out.println("Price = " + price);

		medicineRepository.save(medicine);

		return "redirect:/medicine-list";
	}
	 
	 @GetMapping("/medicine/delete/{id}")
	 public String deleteMedicine(@PathVariable("id") Integer id) {

	     medicineRepository.deleteById(id);

	     return "redirect:/medicine-list";
	 }

	@GetMapping("/doctor/register")
	public String showDoctorForm() {
		return "doctor";
	}

	@GetMapping("/doctors")
	public String viewDoctors(Model model) {
		model.addAttribute("doctors", doctorRepository.findAll());
		return "doctor-list";
	}

	@GetMapping("/doctor/delete/{id}")
	public String deleteDoctor(@PathVariable("id") int id) {
		doctorRepository.deleteById(id);
		return "redirect:/doctors";
	}

	@GetMapping("/patient/register")
	public String showPatientForm() {
		return "patient";
	}

	@PostMapping("/patient/register")
	public String registerPatient(@RequestParam("patientName") String patientName, @RequestParam("age") Integer age,
			@RequestParam("disease") String disease, Model model) {

		Patient patient = new Patient();
		patient.setPatientName(patientName);
		patient.setAge(age);
		patient.setDisease(disease);

		patientRepository.save(patient);

		model.addAttribute("name", patientName);
		model.addAttribute("age", age);
		model.addAttribute("disease", disease);

		patientRepository.save(patient);
		return "redirect:/patients";
	}

	@PostMapping("/doctor/register")
	public String registerDoctor(@RequestParam("doctorName") String doctorName,
			@RequestParam("specialization") String specialization, @RequestParam("phoneNumber") String phoneNumber) {

		Doctor doctor = new Doctor();

		doctor.setDoctorName(doctorName);
		doctor.setSpecialization(specialization);
		doctor.setPhoneNumber(phoneNumber);

		doctorRepository.save(doctor);

		return "redirect:/doctor-list";
	}

	@PostMapping("/appointment/save")
	public String saveAppointment(@RequestParam("patientName") String patientName,
			@RequestParam("doctorName") String doctorName, @RequestParam("appointmentDate") String appointmentDate) {

		Appointment appointment = new Appointment();

		appointment.setPatientName(patientName);
		appointment.setDoctorName(doctorName);
		appointment.setAppointmentDate(appointmentDate);

		appointmentRepository.save(appointment);

		return "redirect:/appointments";
	}

	@GetMapping("/patients")
	public String viewPatients(Model model) {
		model.addAttribute("patients", patientRepository.findAll());
		return "patient-list";
	}

	@GetMapping("/appointments")
	public String viewAppointments(Model model) {
		model.addAttribute("appointments", appointmentRepository.findAll());
		return "appointment-list";
	}

	@GetMapping("/patient/delete/{id}")
	public String deletePatient(@PathVariable("id") int id) {
		patientRepository.deleteById(id);
		return "redirect:/patients";
	}

	@GetMapping("/appointment/delete/{id}")
	public String deleteAppointment(@PathVariable("id") int id) {
		appointmentRepository.deleteById(id);
		return "redirect:/appointments";
	}
	

	

}